// yyyyMMddHHmmss
Date.prototype.dateFormat=function(format){

    var year = this.getFullYear();
    var month = gfLpad(this.getMonth() + 1, 2 , '0');
    var date = gfLpad(this.getDate(), 2 , '0');
    var hour = gfLpad(this.getHours(), 2 , '0');
    var minute = gfLpad(this.getMinutes(), 2 , '0');
    var second = gfLpad(this.getSeconds(), 2 , '0') ;

    format = format.replace('yyyy', year);
    format = format.replace('MM', month);
    format = format.replace('dd', date);
    format = format.replace('HH', hour);
    format = format.replace('mm', minute);
    format = format.replace('ss', second);

    return format;
}

function gfLpad(s, padLength, padString){

    while((s+"").length < padLength)
        s = padString + s;
    return s;
}