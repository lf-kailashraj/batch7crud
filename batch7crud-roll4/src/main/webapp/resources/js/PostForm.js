/**
 * Created by pratishshr on 2/1/16.
 */

function PostForm() {
    var deleteBtn = document.getElementsByClassName('delete-btn');
    var logoutBtn = document.getElementsByClassName('logout-btn')[0];

    var that = this;

    this.init = function () {
        for (var i = 0; i < deleteBtn.length; i++) {
            deleteBtn[i].onclick = function (e) {
                e.preventDefault();
                var href = this.href;
                var confirmation = confirm('do you want to delete?');

                if (confirmation) {
                    that.submitForm(href);
                }
            }
        }

        logoutBtn.onclick = function (e) {
            e.preventDefault();
            that.submitForm(logoutBtn.href);
        }
    }

    this.submitForm = function (href) {
        var submitHref = href;
        var form = document.createElement('form');
        form.action = submitHref;
        form.method = 'post';
        document.body.appendChild(form);
        form.submit();
    }
}

var postForm = new PostForm();
postForm.init();

