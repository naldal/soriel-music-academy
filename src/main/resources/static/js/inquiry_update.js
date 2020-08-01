 $(document).ready(function(){
    $("#updateBtn").click(function (){
        var data = {
            title : $("#title").val(),
            content : $("#content").val(),
            writer_id : $("#writer_id").val()
        };
        var id = $("#id").val();
        alert(data.content);

        $.ajax({
            type: 'PUT',
            url: '/post/update/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data){
            alert('수정 완료');
            window.location.href="/inquire_view/"+id;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    });
});

