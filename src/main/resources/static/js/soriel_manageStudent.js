$(document).ready(function(){

    $(".hidden_update").hide();

    $(".stud_id").hide();
    $(".stud_name").hide();
    $(".stud_link").hide();


    $(".point_update").click(function(){


        $(".stud_id").show();
        $(".stud_name").show();
        $(".stud_link").show();

        $(".point_update").hide();
        $(".point_delete").hide();
        $(".hidden_update").show();

        $(".hidden_update").click(function(){
            const data = {
                id : $(".stud_id").val(),
                student_name : $(".stud_name").val(),
                youtube_link : $(".stud_link").val()
            };
            alert(data.id);

            $.ajax({
                type: 'POST',
                url: '/admin/updateStudent/'+data.id,
                dataType: 'text',
                contentType: 'application/json, charset=utf-8',
                data: data
            }).done(function(data){
                alert('수정완료');
                window.location.href = window.location.href;
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
        });



    });

});