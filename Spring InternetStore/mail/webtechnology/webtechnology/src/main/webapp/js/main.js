function clickFilter(elementId) {
	var elem = document.getElementById(elementId);
	
	var xhr = new XMLHttpRequest();
	
	xhr.open('POST', '/person/filter', true);
	
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4) {
			if(xhr.status == 200) {
				fillTable(JSON.parse(xhr.responseText));
			} else {
				alert('Ajax error!');
			}
		}
	}
	
	xhr.send('q=' + elem.value);
	
}


function fillTable(data) {
	var tr = document.querySelectorAll('#personList tr');
	tr.forEach(function(elem, i) {
		if(!elem.classList.contains('column-header')) {
			elem.remove();
		}
	});
	
	var i=0;
	
	data.forEach(function(elem) {
		var newTr = document.querySelector('#templateRow').cloneNode(true);
		newTr.id = elem.id;
		
		if (i++ % 2 == 0) {
			newTr.className = 'even';
		} else {
			newTr.className = 'odd';
		}

		var td = newTr.querySelectorAll('td');
		
		td[0].innerText = elem.firstName;
		td[1].innerText = elem.lastName;
		
		href = td[2].querySelector('a');
		
		href.href = "/person/modify?id=" + elem.id;
		
		document.querySelector('#personList').appendChild(newTr);
	});
			
}

