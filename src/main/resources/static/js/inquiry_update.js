 $(document).ready(function(){
    $("#updateBtn").click(function (){
        var data = {
            id: $("#id").val(),
            title : $("#title").val(),
            content : $("#content").val(),
            writer_id : $("#writer_id").val()
        };

        $.ajax({
            type: 'PUT',
            url: '/post/update/'+data.id,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('수정 완료');
            window.location.href="/inquire_view/"+data.id;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    });
});

