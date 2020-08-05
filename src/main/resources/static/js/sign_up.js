$(document).ready(function(){
    $("#signup").click(function(){
        if($("#name").val() == '') {
            $("#name").attr('placeholder',"아이디가 공란입니다.");
            $("#name").animate({
                "backgroundColor": 'rgba(225, 112, 85, 0.2)'
            });
        } else if( $("#email").val() == '') {
            $("#email").attr('placeholder', '이메일이 공란입니다');
            $("#email").animate({
                'backgroundColor': 'rgba(225, 112, 85,0.2)'
            });
        } else if ( $("#pass").val() == ''){
            $("#pass").attr('placeholder', '비밀번호가 공란입니다');
            $("#pass").animate({
                'backgroundColor': 'rgba(225, 112, 85,0.2)'
            });
        } else if( $("#pass").val() != $("#re_pass").val()) {
            $("#re_pass").attr('placeholder', '비밀번호가 일치하지 않습니다');
            $("#re_pass").animate({
                'backgroundColor': 'rgba(225, 112, 85,0.2)'
            });
        } else {
            $("#register-form").submit();
        }
    });

    $("#name").focusout(function(){
        if($("#name").val()!='') {
            $("#name").animate({
                'backgroundColor': 'rgb(231, 240, 255)'
            }, 1000);
        }

        let name =  $("#name").val();

        $.ajax({
            type: 'GET',
            async: false,
            url: '/verify_id/'+name,
            dataType: 'json',
            contentType: 'application/json charset=utf-8',
            data: JSON.stringify(name),
        }).done(function(data){
            if(data == false) {
                alert('중복된 아이디 입니다!');
                 $("#name").animate({
                    "backgroundColor": 'rgba(225, 112, 85, 0.2)'
                });
            } else {
                $("#name").animate({
                    'backgroundColor': 'rgb(231, 240, 255)'
                }, 1000);
            }
        }).fail(function(error){
            $("#name").animate({
                "backgroundColor": 'rgba(225, 112, 85, 0.2)'
            });
        });
    });

    $("#email").focusout(function(){
        if($("#email").val()!='') {
            $("#email").animate({
                'backgroundColor': 'rgb(231, 240, 255)'
            }, 1000);
        }
    });

    $("#pass").focusout(function(){
        if($("#pass").val()!='') {
            $("#pass").animate({
                'backgroundColor': 'rgb(231, 240, 255)'
            }, 1000);
        }

    });

    $("#re_pass").focusout(function(){
        if($("#pass").val() != $("#re_pass").val()){
            $("#re_pass").attr('placeholder', '비밀번호가 일치하지 않습니다');
            $("#re_pass").animate({
                'backgroundColor': 'rgba(225, 112, 85, 0.2)'
            }, 1000);
        } else if($("#re_pass").val()!='') {
          $("#re_pass").animate({
              'backgroundColor': 'rgb(231, 240, 255)'
          }, 1000);
        }
    });
});