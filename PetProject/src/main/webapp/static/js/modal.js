document.getElementById("button").addEventListener('click', function () {
    document.querySelector('.bg-modal').style.display = 'flex';
    processSavedData()
});

document.querySelector('.close').addEventListener('click', function () {
   document.querySelector('.bg-modal').style.display = 'none';

});

/*var s = document.querySelector('.bg-modal').style;
s.opacity = 1;
(function fade(){(s.opacity-=.1)<0?s.display="none":setTimeout(fade,40)})();*/
function processSavedData()
{
    var storedPicture = document.querySelectorAll(".corp-center");
    var storedPictures = [];
    for (var i = 0; i < storedPicture.length; i++) {
        storedPictures.push(storedPicture[i].src);

    }

    var hiddenStoredPictures = document.getElementById("HStoredPictures");
    hiddenStoredPictures.value = storedPictures;


    //jQuery
    /* $("#vehicles").submit(); */
}