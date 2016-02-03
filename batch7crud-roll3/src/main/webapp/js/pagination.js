var paginationWrp = document.getElementsByClassName('pagination-wrp')[0];

var curPage = 1;

function paginate(totalPage, page) {
	curPage = page;
	if (page > 1) {
		createPageLink(page - 1, '<');
	}
	if (totalPage <= 6) {
		for (var i = 1; i <= 6; i++)
			createPageLink(i);
	} else if (page <= 3) {
		for (var i = 1; i <= 3; i++)
			createPageLink(i);
		createSpan();
		createPageLink(totalPage - 1);
		createPageLink(totalPage);
	} else if (page >= totalPage - 2) {
		createPageLink(1);
		createPageLink(2);
		createSpan();
		for (var i = totalPage - 2; i <= totalPage; i++)
			createPageLink(i);
	} else {
		createPageLink(1);
		createPageLink(2);
		createSpan();
		createPageLink(page);
		createPageLink(parseInt(page) + 1);
		if (page != totalPage - 3)
			createSpan();
		createPageLink(totalPage - 1);
		createPageLink(totalPage);
	}
	if (totalPage > page)
		createPageLink(parseInt(page) + 1, '>');

}
function createPageLink(page, character) {
	var newEl = document.createElement('a');
	var span = document.createElement('span');
	span.classList.add('pagination');
	newEl.classList.add('page');
	if (character)
		span.innerHTML = character;
	else
		span.innerHTML = page;
	newEl.setAttribute('href', 'students?page=' + page);
	if (curPage == page)
		span.classList.add('pagination-selected');
	newEl.appendChild(span);
	paginationWrp.appendChild(newEl);
}
function createSpan() {
	var newSpan = document.createElement('span');
	newSpan.classList.add('pagination-space');
	newSpan.innerHTML = "..";
	paginationWrp.appendChild(newSpan);

}