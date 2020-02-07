
var fractalPictures = document.querySelector(".message");
var btn = document.getElementById("btn");

btn.addEventListener("click", function () {

    var myRequest = new XMLHttpRequest();
    myRequest.open('GET', 'http://localhost:8888/JSON');
    myRequest.onload = function() {
        var myData = JSON.parse(myRequest.responseText);
        var myDataLength = myData.length;
        console.log(myDataLength);
        console.log(myData[myDataLength-1].Tree.path);
        console.log(myData);
        renderHTML(myData);

    };
    myRequest.send();
});








function renderHTML(data) {
  var htmlString = "";

    htmlString += "<img id='"+data[data.length-1].Tree.id+"' class='corp-center' src='" + data[data.length-1].Tree.path + "'/>";


  fractalPictures.insertAdjacentHTML('beforeend', htmlString);
}







/*
function readTextFile(file, callback) {
  var rawFile = new XMLHttpRequest();
  rawFile.overrideMimeType("application/json");
  rawFile.open("GET", file, true);
  rawFile.onreadystatechange = function() {
    if (rawFile.readyState === 4 && rawFile.status == "200") {
      callback(rawFile.responseText);
    }
  }
  rawFile.send(null);
}

//usage:
readTextFile("/static/json/tree.json", function(text){
  var data = JSON.parse(text);
  console.log(data);
});*/