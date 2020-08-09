$(document).ready(function () {
    var id = $("#id").val();

    $("#reply_update_div").hide();
    $("#do_reply_updateBtn").hide();
    $("#reply").hide();
    $(".spc").hide();

    $("#replyBtn").click(function () {
        $("#deleteBtn").hide();
        $("#replyBtn").hide();
        $("#reply").show();
        $(".spc").show();

    });

    $("#deleteBtn").click(function(){
        $.ajax({
            type: 'DELETE',
            url: '/post/delete/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: id,
        }).done(function(data) {
            alert('글이 삭제되었습니다.');
            window.location.href="/inquire_board";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    });


    $("#reply_submit").click(function(){
        let post_id = $("#id").val();
        var data = {
            reply_content: $("#send_reply").val(),
            post_id: $("#id").val()
        };

        $.ajax({
            type: 'POST',
            url: '/post/reply/'+post_id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data){
            alert('답신 완료');
            window.location.href = window.location.href;
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    });

    $("#reply_deleteBtn").click(function(){
        var reply_id = $("#reply_id").val();

        $.ajax({
            type: 'DELETE',
            url: '/post/reply/'+reply_id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(data){
            alert('답글 삭제 완료');
            window.location.href = window.location.href;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    });

    $("#reply_updateBtn").click(function(){
        $("#reply_update_div").show();
        $("#reply_content_div").hide();
        $("#do_reply_updateBtn").show();
        $("#reply_deleteBtn").hide();
        $("#reply_updateBtn").hide();

        const toreply = $("#reply_content").text();
        $("#reply_content_update").val(toreply);
    });

    $("#do_reply_updateBtn").click(function() {
        let reply_id = $("#reply_id").val();

        var data = {
            reply_id: $("#reply_id").val(),
            reply_content: $("#reply_content_update").val()
        };

        $.ajax({
            type: 'PUT',
            url: '/post/reply/'+reply_id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data){
            alert('답글이 수정되었습니다');
            window.location.href = window.location.href;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    });
});
