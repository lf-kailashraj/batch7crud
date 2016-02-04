package com.lftechnology.batch7crud.utils.requestmapper;

import com.lftechnology.batch7crud.constant.PageConstant;
import com.lftechnology.batch7crud.controller.AuthenticationController;
import com.lftechnology.batch7crud.controller.BookController;
import com.lftechnology.batch7crud.controller.StudentController;
import com.lftechnology.batch7crud.controller.TestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author binodnme
 * Created on 1/29/16
 */
public class RequestMapper {
  private static final Logger LOGGER = Logger.getLogger(RequestMapper.class.getName());


  public void map(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    String servletPath = request.getServletPath();
    String pathInfo = request.getPathInfo();
    String requestType = request.getMethod();

    Class studentController = StudentController.class;
    Class authenticationController = AuthenticationController.class;
    Class bookController = BookController.class;
    Class testController = TestController.class;
    List<Class> controllers = new ArrayList<>();
    controllers.add(studentController);
    controllers.add(authenticationController);
    controllers.add(bookController);
    controllers.add(testController);



    Class controller = filterController(controllers, servletPath);

    if(controller == null){
      throw new ServletException(PageConstant.PAGE_NOT_FOUND);
    }

    Method[] methods = controller.getDeclaredMethods();
    List<Method> methodList = Arrays.asList(methods);

    Method method = filterMethod(methodList, pathInfo, requestType);


    if(method != null){
      Object obj;
      try {
        obj = controller.newInstance();
        method.setAccessible(true);
        method.invoke(obj, request, response);
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
    }else {
      throw new ServletException(PageConstant.PAGE_NOT_FOUND);
    }
  }


  private Class filterController(List<Class> controllers, String servletPath){
    List<String> servletPathTokens = getPathAsTokenList(servletPath);


    for(Class controller : controllers){
      if(controller.isAnnotationPresent(WebServlet.class)){
        WebServlet webServlet = (WebServlet) controller.getAnnotation(WebServlet.class);

        List<String> webServletValueTokens = getPathAsTokenList(webServlet.value()[0]);

        if(isSame(servletPathTokens, webServletValueTokens)){
          return controller;
        }
      }
    }
    return null;
  }

  private List<String> getPathAsTokenList(String path){
    String[] pathTokensAsArray = path.split("/");
    List<String> pathTokensAsList = new ArrayList<>();
    for(String token : pathTokensAsArray){
      if(token != null && !token.isEmpty() && !token.startsWith("*")){
        pathTokensAsList.add(token);
      }
    }

    return pathTokensAsList;
  }

  private Boolean isSame(List<String> param1, List<String> param2){
    if(param1.size() != param2.size())
      return false;

    for (int i = 0; i < param1.size(); i++) {
      if(!param1.get(i).equalsIgnoreCase(param2.get(i)))
        return false;
    }

    return true;

  }

  private Method filterMethod(List<Method> methodList, String pathInfo, String requestType){
    //filter method by annotation
    List<Method> annotatedMethods = filterMethodByAnnotation(RequestMapping.class, methodList);

    //filter method by request type (GET or POST)
    annotatedMethods = filterMethodByRequestType(annotatedMethods, requestType);

    //filter method by path size
    annotatedMethods = filterMethodByPathSize(annotatedMethods, pathInfo);

    return filterRequestedMethod(pathInfo,0, annotatedMethods);
  }

  private Method filterRequestedMethod(String pathInfo, Integer index, List<Method> methods){ // NOSONAR
    String[] pathParts = pathInfo.split("/");
    List<String> pathParts1 = new ArrayList<>();
    for(String p : pathParts){
      if(p != null && !p.isEmpty()){
        pathParts1.add(p);
      }
    }

    List<Method> candidateMethods = new ArrayList<>();
    for(Method method : methods){
      RequestMapping mapping = method.getAnnotation(RequestMapping.class);
      String value = mapping.value();

      String[] valueParts = value.split("/");

      /*
      * condition 1 : if the index value is less than that of value parts length
      * condition 2 : if the value at index is variable type
      * condition 3 : if the value at index matches path parts at index
      * */
      if(index < valueParts.length && (isVariable(valueParts[index]) || valueParts[index].equalsIgnoreCase(pathParts1.get(index)))){
        candidateMethods.add(method);

      }
    }


    if(index < pathParts1.size() - 1){
      Integer nextIndex = index+1;
      return  filterRequestedMethod(pathInfo, nextIndex, candidateMethods);
    }else{
      if(candidateMethods.size() == 1){
        return candidateMethods.get(0);
      }else {
        return null;
      }
    }

  }

  private List<Method> filterMethodByAnnotation(Class<? extends Annotation> annotationClass, List<Method> methods){
    List<Method> ms = new ArrayList<>();
    for(Method m : methods){
      if(m.isAnnotationPresent(annotationClass)){
        ms.add(m);
      }
    }
    return ms;
  }

  private List<Method> filterMethodByRequestType(List<Method> methods, String requestType){
    List<Method> results = new ArrayList<>();
    for(Method method : methods){
      RequestMapping mapping = method.getAnnotation(RequestMapping.class);
      if(requestType.equalsIgnoreCase(mapping.method())){
        results.add(method);
      }
    }

    return results;
  }

  private List<Method> filterMethodByPathSize(List<Method> methods, String pathInfo){
    String[] pathParts = pathInfo.split("/");
    List<String> pathParts1 = new ArrayList<>();
    for(String p : pathParts){
      if(p != null && !p.isEmpty()){
        pathParts1.add(p);
      }
    }

    List<Method> results = new ArrayList<>();
    for(Method method : methods){
      RequestMapping mapping = method.getAnnotation(RequestMapping.class);
      String value = mapping.value();
      String[] valueParts = value.split("/");

      if(valueParts.length == pathParts1.size()){
        results.add(method);
      }
    }

    return results;
  }

  private Boolean isVariable(String part){
    return part.startsWith("{") && part.endsWith("}");
  }

}
