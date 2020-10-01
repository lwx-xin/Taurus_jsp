
document.oncontextmenu = function(){
	return false;
};//禁止右键
/*document.onkeydown = function () {
    if (window.event && window.event.keyCode == 123) {
        event.keyCode = 0;
        event.returnValue = false;
        return false;
    }
};*///禁止F12

/**
* 使用时候直接调用方法
* Format(date,"yyyy-MM-dd HH:mm");输出格式为 "2015-10-14 16:50"；
* 第一个参数为时间，
* 第二个参数为输出格式。
*
*
* @param now
* @param mask
* @returns {*}
* @constructor
*/
function formatDate(now, mask) {
   var d = now;
   var zeroize = function (value, length) {
       if (!length) length = 2;
       value = String(value);
       for (var i = 0, zeros = ''; i < (length - value.length); i++) {
           zeros += '0';
       }
       return zeros + value;
   };

   return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0) {
       switch ($0) {
           case 'd':
               return d.getDate();
           case 'dd':
               return zeroize(d.getDate());
           case 'ddd':
               return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
           case 'dddd':
               return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
           case 'M':
               return d.getMonth() + 1;
           case 'MM':
               return zeroize(d.getMonth() + 1);
           case 'MMM':
               return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
           case 'MMMM':
               return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
           case 'yy':
               return String(d.getFullYear()).substr(2);
           case 'yyyy':
               return d.getFullYear();
           case 'h':
               return d.getHours() % 12 || 12;
           case 'hh':
               return zeroize(d.getHours() % 12 || 12);
           case 'H':
               return d.getHours();
           case 'HH':
               return zeroize(d.getHours());
           case 'm':
               return d.getMinutes();
           case 'mm':
               return zeroize(d.getMinutes());
           case 's':
               return d.getSeconds();
           case 'ss':
               return zeroize(d.getSeconds());
           case 'l':
               return zeroize(d.getMilliseconds(), 3);
           case 'L':
               var m = d.getMilliseconds();
               if (m > 99) m = Math.round(m / 10);
               return zeroize(m);
           case 'tt':
               return d.getHours() < 12 ? 'am' : 'pm';
           case 'TT':
               return d.getHours() < 12 ? 'AM' : 'PM';
           case 'Z':
               return d.toUTCString().match(/[A-Z]+$/);
           // Return quoted strings with the surrounding quotes removed
           default:
               return $0.substr(1, $0.length - 2);
       }
   });
};

//取消系统自带的右击事件
$("body").contextmenu(function(){
	return false;
});

//禁止页面文字被选中
$("body").css({"-moz-user-select": "none",
	"-webkit-user-select": "none",
	"-ms-user-select": "none",
	"-khtml-user-select": "none",
	"user-select": "none"});

function isNull(data){
	if(data=="" || data==null || JSON.stringify(data)=="[]" || JSON.stringify(data)=="{}"){
		return true;
	}
	return false;
}

function isNotNull(data){
	return !isNull(data);
}

function formatStr(data){
	if(isNull(data)){
		return "";
	}
	return data;
}

function loading(){
	//加载动画
	var html = '<div class="loading-wrapper">'+
	'<div class="loading-spinner-box">'+
	'	<div class="loading-configure-border-1">'+
	'		<div class="loading-configure-core"></div>'+
	'	</div>'+
	'	<div class="loading-configure-border-2">'+
	'		<div class="loading-configure-core"></div>'+
	'	</div>'+
	'</div>'+
	'</div>';
	$("body").append(html);
	
	//打开遮罩层
	$("body").append("<div class='mask'></div>");
	$(".mask").fadeIn();
}

function removeLoading(){
	//移除加载动画
	$(".loading-wrapper").remove();
	//移除遮罩层
	$(".mask").fadeOut();
	$(".mask").remove();
}
