$(document).ready(function(){

    $(".hidden_update").hide();
    $(".cancel").hide();

    $(".stud_id").hide();
    $(".stud_name").hide();
    $(".stud_link").hide();

    $(".newStudent").hide();

    $(".point_update").click(function(){
        $showing_name = $(this).parents('tr').children('th').siblings('td').children('.showing_name');
        $input_name = $(this).parents('tr').children('th').siblings('td').children('.stud_name');
        $showing_link = $(this).parents('tr').children('th').siblings('td').children('.showing_link');
        $input_link = $(this).parents('tr').children('th').siblings('td').children('.stud_link');
        $hidden_update = $(this).next();
        $point_delete = $(this).parent().siblings().children('.point_delete');
        $cancel = $(this).parent().siblings().children('.cancel');

        if( $(this).attr('id') == $(this).parents('tr').children('th').attr('id')){
            $showing_name.hide();
            $input_name.show();
            $showing_link.hide();
            $input_link.show();
            $(this).hide();
            $hidden_update.show();
            $point_delete.hide();
            $cancel.show();
        }

        $cancel.click(function(){
            $point_update = $(this).parent().siblings().children('.point_update');
            $hidden_update = $(this).parent().siblings().children('.hidden_update');

            $(this).hide();
            $input_link.hide();
            $input_name.hide();
            $hidden_update.hide();
            $showing_name.show();
            $showing_link.show();
            $point_update.show();
            $point_delete.show();
        });

        $(".hidden_update").click(function(){
            $point_update = $(this).prev();

            const data = {
                id : $(this).attr('id'),
                student_name : $input_name.val(),
                youtube_link : $input_link.val()
            };

            $.ajax({
                type: 'POST',
                url: '/admin/manage/'+data.id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function(ajaxReturn){
                alert('수정완료');
                $showing_name.text(ajaxReturn.student_name);
                $showing_link.text(ajaxReturn.youtube_link);
                $showing_link.show();
                $showing_name.show();
                $input_name.hide();
                $input_link.hide();
                $(".hidden_update").hide();
                $point_update.show();
                $point_delete.show();

                /*window.location.href = window.location.href;*/
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
        });
    });

    $(".point_delete").click(function (){
        const del_id = $(this).attr('id');
        alert(del_id);

        $.ajax({
            type: 'DELETE',
            url: '/admin/manage/'+del_id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(del_id)
        }).done(function(data){
            if(data==true) {
                alert('멤버 삭제완료');
                window.location.href = window.location.href;
            } else if(data==false) {
                alert('회원 삭제실패');
            }
        }).fail(function(e){
            alert(JSON.stringify(e));
            alert('회원이 없습니다.');
        });
    });

    $("#enroll").click(function(){
        $(".newStudent").show();
    });

    $(".enrollBtn").click(function (){

        const enrolldata = {
            student_name: $(".newName").val(),
            youtube_link: $(".newLink").val()
        }

        $.ajax({
            type: 'POST',
            url: '/admin/manage/enroll',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(enrolldata)
        }).done(function(){
            alert('등록 성공');
            window.href.location = window.href.location;
        }).fail(function(e){
            alert(JSON.stringify(e));
            alert('등록 실패');
        });
    });


});