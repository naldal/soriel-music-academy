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
            alert(JSON.stringify(error));
        });
    });

    $(".card-body").hover(function(){
        $("#myself").css('background-color','#dddddd');
    });
});
