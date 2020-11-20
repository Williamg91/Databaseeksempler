function CheckPassword(inputtxt,mail)
{
    var mail = mail.value();
    var passw=  /^[A-Za-z]\w{7,14}$/;
    if(inputtxt.value.match(passw))
    {
        //alert('Correct, try another...')
        window.location.replace("su4.eduhost.dk/cgi-bin/validate?password="+passw+"&mail="+mail)
        return true;
    }
    else
    {
        alert('Wrong...!')

        return false;
    }
}