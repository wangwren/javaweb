
function init(){
	makeYear();
	makeMonth();
	makeDay();
}

function makeYear(){
	
	var year = document.getElementById("year");
	
	for(var i=1901;i<=new Date().getFullYear();i++){
		
		var option = document.createElement("option");
		option.value = i;
		option.innerText = i;
		year.appendChild(option);
	}
}

function makeMonth(){
	
	var month = document.getElementById("month");
	for(var i=2;i<=12;i++){
		var option = document.createElement("option");
		if(i < 10){
			option.value= "0" + i;
			option.innerText = "0" + i;
		}else{
			option.value = i;
			option.innerText = i;
		}
		month.appendChild(option);
	}
}

function makeDay(){
	var day = document.getElementById("day");
	for(var i=2;i<=31;i++){
		var option = document.createElement("option");
		if(i < 10){
			option.value= "0" + i;
			option.innerText = "0" + i;
		}else{
			option.value = i;
			option.innerText = i;
		}
		day.appendChild(option);
	}
}

function dosubmit(){
	
	makeBirthday();
	makePreferences();
	return true;
}

function makeBirthday(){
	
	var year = document.getElementById("year").value;
	var month = document.getElementById("month").value;
	var day = document.getElementById("day").value;
	
	var birthday = year + "-" + month + "-" + day;
	
	//创建一个隐藏域，将日期挂在隐藏域中
	var input = document.createElement("input");
	input.type = "hidden";
	input.value = birthday;
	input.name = "birthday";
	
	document.getElementById("customer").appendChild(input);
}


function makePreferences(){
	
	var preference = "";
	var pres = document.getElementsByName("pre");
	for(var i=0;i<pres.length;i++){
		if(pres[i].checked == true){
			preference = preference + pres[i].value + ",";
		}
	}
	
	preference = preference.substring(0, preference.length - 1);
	
	var input = document.createElement("input");
	input.type = "hidden";
	input.value = preference;
	input.name = "preference";
	
	document.getElementById("customer").appendChild(input);
}


