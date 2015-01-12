//======================= Javascript Utilities ===============================
/**
 * 获得当前url中的参数值
 * @example
 *  the current page url is "http://some/path/save.do?uid=32&act=1"<br/>
 *  so, getUrlParam('uid')=32
 * @param {string}
 *            name 参数名 
 * @author Huang Zhenkun
 * 
 */
var getUrlParam = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}

/**
 * @example
 *  the current page url is "http://some/path/uid/12/mid/44/save"<br/>
 *  so, getUrlRestfulParam('uid')=12, getUrlRestfulParam('mid')=44
 *  @author Huang Zhenkun
 */
var getUrlRestfulParam =function(name){
	var reg= new RegExp(name+"\\\/(\\d+)");
	var r=window.location.href.match(reg);
	return r?r[1]:null;
}




/**
 * 
 * 
   |token   |   description        |  example    |
	#YYYY#     4-digit year             1999
	#YY#       2-digit year             99
	#MMMM#     full month name          February
	#MMM#      3-letter month name      Feb
	#MM#       2-digit month number     02
	#M#        month number             2
	#DDDD#     full weekday name        Wednesday
	#DDD#      3-letter weekday name    Wed
	#DD#       2-digit day number       09
	#D#        day number               9
	#th#       day ordinal suffix       nd
	#hhh#      military/24-based hour   17
	#hh#       2-digit hour             05
	#h#        hour                     5
	#mm#       2-digit minute           07
	#m#        minute                   7
	#ss#       2-digit second           09
	#s#        second                   9
	#ampm#     "am" or "pm"             pm
	#AMPM#     "AM" or "PM"             PM
 * 
 * usage examples:
 * @example DateUtils.format(new Date(1423243864403),"#YYYY# #MMM# #D#") = "2015 Feb 7"
 * @example DateUtils.format(1423243864403) = "2015/02/07 01:31:04"
 * @example DateUtils.format()="2014/12/02 06:56:20"   <--current date
 * @link http://stackoverflow.com/questions/4673527/converting-milliseconds-to-a-date-jquery-js
 * @param date {int or Date} optional
 * @param formatString  optional
 * @returns {string}
 * @author Huang Zhenkun
 */
var DateUtils = {};
DateUtils.ISO8601_DATETIME = "#YYYY#-#MM#-#DD# #hh#:#mm#:#ss#";
DateUtils.ISO8601_DATE = "#YYYY#-#MM#-#DD#";
DateUtils.defaultFormatString = DateUtils.ISO8601_DATETIME;
DateUtils.format = function(date,formatString){
    var YYYY,YY,MMMM,MMM,MM,M,DDDD,DDD,DD,D,hhh,hh,h,mm,m,ss,s,ampm,AMPM,dMod,th;
    var dateObject;
    if (date===null){
    	return null;
    }else if (date===undefined){
    	dateObject= new Date();
    }else if (typeof(date)==="number"){    	
    	dateObject = new Date(date);
    }else{
    	dateObject = date;
    }
     
    formatString = formatString || DateUtils.defaultFormatString;
    YY = ((YYYY=dateObject.getFullYear())+"").slice(-2);
    MM = (M=dateObject.getMonth()+1)<10?('0'+M):M;
    MMM = (MMMM=["January","February","March","April","May","June","July","August","September","October","November","December"][M-1]).substring(0,3);
    DD = (D=dateObject.getDate())<10?('0'+D):D;
    DDD = (DDDD=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"][dateObject.getDay()]).substring(0,3);
    th=(D>=10&&D<=20)?'th':((dMod=D%10)==1)?'st':(dMod==2)?'nd':(dMod==3)?'rd':'th';
    formatString = formatString.replace("#YYYY#",YYYY).replace("#YY#",YY).replace("#MMMM#",MMMM).replace("#MMM#",MMM).replace("#MM#",MM).replace("#M#",M).replace("#DDDD#",DDDD).replace("#DDD#",DDD).replace("#DD#",DD).replace("#D#",D).replace("#th#",th);

    h=(hhh=dateObject.getHours());
    if (h==0) h=24;
    if (h>12) h-=12;
    hh = h<10?('0'+h):h;
    AMPM=(ampm=hhh<12?'am':'pm').toUpperCase();
    mm=(m=dateObject.getMinutes())<10?('0'+m):m;
    ss=(s=dateObject.getSeconds())<10?('0'+s):s;
    var res= formatString.replace("#hhh#",hhh).replace("#hh#",hh).replace("#h#",h).replace("#mm#",mm).replace("#m#",m).replace("#ss#",ss).replace("#s#",s).replace("#ampm#",ampm).replace("#AMPM#",AMPM);
    return res;
}


//--------------------------------------------------------------------------------------------------------
//---                  Efforts of Adaption to Microsoft Internet Explorer                              ---
//--------------------------------------------------------------------------------------------------------
/**
 * a simple implementation of JSON.stringify (serialization function) and JSON.parse (de-serialization function),
 *  aiming at being compatible to IE6-IE7
 * By this way,there is no need to import another js file: json2.js(https://github.com/douglascrockford/JSON-js)
 * @example JSON.stringify({});                        // '{}'
 * @example JSON.stringify(true);                      // 'true'
 * @example JSON.stringify("foo");                     // '"foo"'
 * @example JSON.stringify([1, "false", false]);       // '[1,"false",false]'
 * @example JSON.stringify({ x: 5 });                  // '{"x":5}'
 * @link http://stackoverflow.com/questions/3326893/is-json-stringify-supported-by-ie-8
 * @author Huang Zhenkun
 */
var JSON = JSON || {};
JSON.stringify = JSON.stringify || function(obj) {
	var t = typeof (obj);
	if (t != "object" || obj === null) {
		// simple data type
		if (t == "string")
			obj = '"' + obj + '"';
		return String(obj);
	} else {
		// recurse array or object
		var n, v, json = [], arr = (obj && obj.constructor == Array);
		for (n in obj) {
			v = obj[n];
			t = typeof (v);
			if (t == "string")
				v = '"' + v + '"';
			else if (t == "object" && v !== null)
				v = JSON.stringify(v);
			json.push((arr ? "" : '"' + n + '":') + String(v));
		}
		return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
	}
};

// implement JSON.parse de-serialization
JSON.parse = JSON.parse || function(str) {
	if (str === "")
		str = '""';
	eval("var p=" + str + ";");
	return p;
};



/**
 * Avoid `console` errors in browsers that lack a console ,especially in IE.
 * @link http://stackoverflow.com/questions/7742781/why-javascript-only-works-after-opening-developer-tools-in-ie-once
 * @author Huang Zhenkun
 */
(function() {
	var method;
	var noop = function() {
	};
	var op1 = function(arg1, arg2, arg3) {
		alert(arg1);
	};
	var methods = [ 'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error', 'exception', 'group', 'groupCollapsed', 'groupEnd',
			'info', 'log', 'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd', 'timeStamp', 'trace', 'warn' ];
	var length = methods.length;
	var console = (window.console = window.console || {});

	while (length--) {
		method = methods[length];

		// Only stub undefined methods.
		if (!console[method]) {
			console[method] = op1;
		}
	}
}());

/**
 * simple implementation of some functions supporting ECMA262-5 
 * By this way,Array's function 'indexOf' missing problem and other problems alike can be fixed.
 * 
 * @link http://stackoverflow.com/questions/2790001/fixing-javascript-array-functions-in-internet-explorer-indexof-foreach-etc
 * @link http://code.google.com/p/js-methods/
 */
(function() {
	// 'use strict';
	//Add ECMA262-5 method binding if not supported natively
	if (!('bind' in Function.prototype)) {
		Function.prototype.bind = function(owner) {
			var that = this;
			if (arguments.length <= 1) {
				return function() {
					return that.apply(owner, arguments);
				};
			} else {
				var args = Array.prototype.slice.call(arguments, 1);
				return function() {
					return that.apply(owner, arguments.length === 0 ? args : args.concat(Array.prototype.slice.call(arguments)));
				};
			}
		};
	}

	// Add ECMA262-5 string trim if not supported natively
	if (!('trim' in String.prototype)) {
		String.prototype.trim = function() {
			return this.replace(/^\s+/, '').replace(/\s+$/, '');
		};
	}

	// Add ECMA262-5 Array methods if not supported natively
	if (!('indexOf' in Array.prototype)) {
		Array.prototype.indexOf = function(find, i /* opt */) {
			if (i === undefined)
				i = 0;
			if (i < 0)
				i += this.length;
			if (i < 0)
				i = 0;
			for (var n = this.length; i < n; i++)
				if (i in this && this[i] === find)
					return i;
			return -1;
		};
	}
	if (!('lastIndexOf' in Array.prototype)) {
		Array.prototype.lastIndexOf = function(find, i /* opt */) {
			if (i === undefined)
				i = this.length - 1;
			if (i < 0)
				i += this.length;
			if (i > this.length - 1)
				i = this.length - 1;
			for (i++; i-- > 0;)
				/* i++ because from-argument is sadly inclusive */
				if (i in this && this[i] === find)
					return i;
			return -1;
		};
	}
	if (!('forEach' in Array.prototype)) {
		Array.prototype.forEach = function(action, that /* opt */) {
			for (var i = 0, n = this.length; i < n; i++)
				if (i in this)
					action.call(that, this[i], i, this);
		};
	}
	if (!('map' in Array.prototype)) {
		Array.prototype.map = function(mapper, that /* opt */) {
			var other = new Array(this.length);
			for (var i = 0, n = this.length; i < n; i++)
				if (i in this)
					other[i] = mapper.call(that, this[i], i, this);
			return other;
		};
	}
	if (!('filter' in Array.prototype)) {
		Array.prototype.filter = function(filter, that /* opt */) {
			var other = [], v;
			for (var i = 0, n = this.length; i < n; i++)
				if (i in this && filter.call(that, v = this[i], i, this))
					other.push(v);
			return other;
		};
	}
	if (!('every' in Array.prototype)) {
		Array.prototype.every = function(tester, that /* opt */) {
			for (var i = 0, n = this.length; i < n; i++)
				if (i in this && !tester.call(that, this[i], i, this))
					return false;
			return true;
		};
	}
	if (!('some' in Array.prototype)) {
		Array.prototype.some = function(tester, that /* opt */) {
			for (var i = 0, n = this.length; i < n; i++)
				if (i in this && tester.call(that, this[i], i, this))
					return true;
			return false;
		};
	}
}());

// ======================= jQuery Extensions and Initialization ===============================
/**
 * 扩展jQuery方法：注册回车事件
 * 
 * @requires jQuery 1.7+
 * @author Huang Zhenkun
 * @example $('textarea').pressEnter(function(){alert('here')})
 */
$.fn.pressEnter = function(fn) {

	return this.each(function() {
		$(this).bind('enterPress', fn);
		$(this).keyup(function(e) {
			if (e.keyCode == 13) {
				$(this).trigger("enterPress");
			}
		})
	});
};

/**
 * 初始化ajax方法：禁止缓存
 */
$.ajaxSetup({
	cache : false
});
/**
 * collect all <input> elments by name and value intelligently and return a object.
 * TODO support checkbox,select
 * @requires jQuery 1.7+
 * @author Huang Zhenkun
 */
jQuery.fn.extend({
	readForm : function(paramNames) {
		var res = {};
		var selector = 'input[name="id"],input[name]:enabled,textarea[name]:enabled,select[name]:enabled';
		$(this).filter(selector).each(function() {
			res[this.name] = this.value;
		});
		$(this).find(selector).each(function() {
			res[this.name] = this.value;
		});
		if (paramNames){
			//filter param names
			var resFiltered={},k,i;
			for (i in paramNames){
				k=paramNames[i];
				if (res[k]!=undefined) resFiltered[k]=res[k];
			}
			return resFiltered
		}
		return res;
	},
	fillForm : function(data) {
		for ( var k in data) {
			var sels = [ 
			             'input[name=' + k + ']', 'input[name=' + k.toLowerCase() + ']',
			             'textarea[name=' + k + ']','textarea[name=' + k.toLowerCase() + ']', 
			             'select[name=' + k + ']','select[name=' + k.toLowerCase() + ']'
			             
			            ];
			for ( var i in sels) {
				$(this).find(sels[i]).val(data[k]);
			}
			var sels2 = ['span .'+k,'span .'+k.toLowerCase()]
			for ( var i in sels2) {
				$(this).find(sels2[i]).html(data[k]);
			}
		}
	}
});

/**
 * Simplified Chinese translation for bootstrap-datetimepicker
 * @author Yuan Cheung <advanimal@gmail.com>
 */
(function($){
	if (!$.fn.datetimepicker) return;
	$.fn.datetimepicker.dates['zh-CN'] = {
			days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
			daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
			daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
			months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			today: "今天",
			suffix: [],
			meridiem: ["上午", "下午"]
	};
}(jQuery));
/**
 * select2 i18n 
 * @see https://select2.github.io/
 */
if (window.$ && $.fn && $.fn.select2){
window.$=window.$||{},function(){
	$&&$.fn&&$.fn.select2&&$.fn.select2.amd&&(define=$.fn.select2.amd.define,require=$.fn.select2.amd.require),
	define("select2/i18n/zh-CN",[],function(){return{
	inputTooLong:function(e){var t=e.input.length-e.maximum,n="请删掉"+t+"个字符";return n},
	inputTooShort:function(e){var t=e.minimum-e.input.length,n="请再输入"+t+"个字符";return n},
	loadingMore:function(){return"加载中…"},
	maximumSelected:function(e){var t="你只能选择最多"+e.maximum+"项";return t},
	noResults:function(){return"没有找到相符的项目"},searching:function(){return"查找中…"}}}),
	require("jquery.select2"),
	$.fn.select2.amd={define:define,require:require}
}();
}
/**
 * Bootstrap-select v1.6.3 i18n
 * @see http://silviomoreto.github.io/bootstrap-select/ 
 */
if (window.$ && $.fn && $.fn.selectpicker){
(function ($) {
  $.fn.selectpicker.defaults = {
    noneSelectedText: '没有选中任何项',
    noneResultsText: '没有找到匹配项',
    countSelectedText: '选中{1}中的{0}项',
    maxOptionsText: ['超出限制 (最多选择{n}项)', '组选择超出限制(最多选择{n}组)'],
    multipleSeparator: ', '
  };
}(jQuery));
}
if (window.bootbox){
	bootbox.setDefaults({locale:"zh_CN"});
}
if (window.Messenger){
	Messenger.options = {
	    extraClasses: 'messenger-fixed messenger-on-bottom messenger-on-right',
	    theme: 'flat'
	}
}
//========================== 本项目相关 ===============================
function generate_html_tr(cells){
	var s='';
	for (var i=0;i<cells.length;i++){
		s+= ('<td>'+cells[i]+'</td>');
	}
	return '<tr>'+s+'</tr>';
}
function getRowDataById(rows_data,row_id){
	if (rows_data){ 
		for (var i=0;i<rows_data.length;i++){
			if (rows_data[i].id==row_id) return rows_data[i];
		}
	}
	return null;
}