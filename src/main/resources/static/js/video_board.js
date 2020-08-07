$(document).ready(function() {
    $(".clickhere").click(function() {
        var id = $(this).children('.student_id').val();

        $.ajax({
            type: 'POST',
            url: '/video_board/'+id,
            dataType: 'text',
            contentType: 'application/json, charset=utf-8',
            data: JSON.stringify(id)
        }).done(function(ajaxReturnString) {
            window.location.href = '/video_board/'+id+'?link='+ajaxReturnString
        }).fail(function(error){
            alert('아직 영상이 없습니다');
            window.location.href = '/video_board/'+id;
        });
    });

});
