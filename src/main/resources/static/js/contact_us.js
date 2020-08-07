$(document).ready(function(){
    $("#contactBtn").click(function(e){
        if($("#title").val()==''){
            alert("제목이 비어있습니다.");
            e.preventDefault();
        } else if($("#email").val()==''){
            alert("발신 이메일을 입력해주십시오");
            e.preventDefault();
        } else if($("#message").val()=='') {
            alert("내용을 입력해주세요.");
            e.preventDefault();
        } else {
            $("#contactBtn").submit();
        }
    });

});