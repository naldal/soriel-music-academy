$(document).ready(function() {
    $(".clickhere").click(function() {
        var id = $(this).children('.student_id').val();

        alert(id);

        $.ajax({
            type: 'POST',
            url: '/video_board/'+id,
            dataType: 'text',
            contentType: 'application/json, charset=utf-8',
            data: JSON.stringify(id)
        }).done(function(ajaxReturnString) {
            alert('이동되었습니다.');
            window.location.href = '/video_board/'+id+'?link='+ajaxReturnString
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    });
});
