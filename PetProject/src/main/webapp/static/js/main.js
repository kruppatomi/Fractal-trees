var pictures = document.querySelectorAll(".corp-center");
var pictures1 = document.querySelectorAll(".corp-center1");

for (let picture of pictures) {
    pictures[i].addEventListener('click', function(event) {
        console.log('0:  i was clicked');
        let picId = event.target.id;
        console.log(picId);
        var myRequest = new XMLHttpRequest();
        myRequest.open('GET', 'http://localhost:8888/JSON');
        myRequest.onload = function() {
            var myData = JSON.parse(myRequest.responseText);
            console.log(myData[0]);
            showPicture(myData, selectedId);
    }});
}

for (let picture1 of pictures1) {
    picture1.addEventListener('click', function(event) {
        console.log('1:  i was clicked');

        let selectedsrc = event.target.src;
        let res = selectedsrc.replace("http://localhost:8888", "");
            showPicture(res);
        });
}

function showPicture(res) {
    var image = document.getElementById('picture');
    image.src = res;
}


/*
pictureClick.addEventListener("click", function () {
    var selectedId = this.id;
  var myRequest = new XMLHttpRequest();
  myRequest.open('GET', 'http://localhost:8888/JSON');
  myRequest.onload = function() {
    var myData = JSON.parse(myRequest.responseText);
   console.log(myData[0]);
    showPicture(myData, selectedId);
  };
});
*/


/*pictureClick1.addEventListener("click", function () {
    var selectedId = this.id;
    var myRequest = new XMLHttpRequest();
    myRequest.open('GET', 'http://localhost:8888/JSON');
    myRequest.onload = function() {
        var myData = JSON.parse(myRequest.responseText);
        console.log(myData[0]);
        showPicture(myData, selectedId);
    };
    myRequest.send();
});*/




