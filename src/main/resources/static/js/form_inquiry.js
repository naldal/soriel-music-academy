$(document).ready(function(){
    $("#submitBtn").on('click', dosubmit);

    function dosubmit() {
        if($("#title").val()==''){
            alert("제목이 비어있습니다.");
        } else if( $("#content").val()=='') {
            alert("내용이 비어있습니다.");
        } else {
            $("#form").first().submit();
        }
    }
});

