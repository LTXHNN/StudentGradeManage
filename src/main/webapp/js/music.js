
function getValue(){
    var  myselect=document.getElementById("selectMusic");
    var index=myselect.selectedIndex ;
    //alert(1);
    var myAudio = document.getElementById("audio");
    myAudio.src=myselect.options[index].value;
    myAudio.play();
}
function changeState(){
    var myAudio = document.getElementById("audio");
    if(myAudio.paused){
        myAudio.play();
    }else{
        myAudio.pause();
    }
}
