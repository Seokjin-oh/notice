function gfCallServer(url, data, callback, method, dataType, async, loading){
	
	var _data = null, async = (async==false) ? false : true;
	data = data || {};
	method = method || "POST";
	loading = loading || true;

	if(method.toUpperCase() == "DELETE"){
		url += (url.indexOf("?")==-1?"?":"&") + $.param(data, true);
		data = {};
	}
	
	if(method.toUpperCase() == "POST"){
		data = JSON.stringify(data);
	}
		
	var options = {
		url: url,
		contentType:'application/json; charset=utf-8',
		type: method || "POST",
		dataType : dataType || "json",
		data : data,
		cache: false ,
		async : async
	};

	$.ajax(options).done(function(data, textStatus, jqXHR) {
		_data = data;
		if(typeof callback === "function"){
			callback(data);							
		}else {
			 eval(callback + "(data);");
		}
		
	}).fail(function(jqXHR, textStatus, errorThrown) {
		//TODO JSON DATA  일 떄, HTML 일 떄???
		var status = jqXHR.status;
		if(status == 200) {
			location.href = "/login";
		}
		
	}).always(function( jqXHR, textStatus, errorThrown ) { 

	});
	
	if(async){
		return {
			done : function(fn){userFns.done = fn;},
			fail : function(fn){userFns.fail = fn;},
			always : function(fn){userFns.always = fn;},
		};
	}else{
		return _data;
	}
}

function gfAjaxCallWithMultipartForm(sAction,fData,fnCallback,sMethod, dataType){

    if(sMethod == undefined){
        sMethod = "POST";
    }

    $.ajax({
        url		: sAction,
		enctype: 'multipart/form-data',
		data:fData,
        cache:false,
        contentType:false,
        dataType : dataType || "json",
        processData:false,
        type		: sMethod,
        success	: function(data){        	
            fnCallback(data);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
		console.log("fail" , sAction + " >> ", jqXHR, textStatus, errorThrown);

	}).always(function( jqXHR, textStatus, errorThrown ) {

    });
}


//목록 페이징
Paging = function(totalCnt, rowSize, pageNo, token) {
	
	var pageSize = 10; 
	_totalCnt = parseInt(totalCnt);// 전체레코드수
	_rowSize = parseInt(rowSize); // 페이지당 보여줄 데이타수
	_pageSize = parseInt(pageSize); // 페이지 그룹 범위 1 2 3 5 6 7 8 9 10 
	_pageNo = parseInt(pageNo); // 현재페이지
	var html = new Array(); 
	
	if(_totalCnt > 0) {
		// 페이지 카운트 
		var _pageCnt = _totalCnt % _rowSize;
		if(_pageCnt == 0) {
			_pageCnt = parseInt(_totalCnt / _rowSize);
		} else { 
			_pageCnt = parseInt(_totalCnt / _rowSize) + 1;
		}
		
		var _pRCnt = parseInt(_pageNo / _pageSize);
		if(_pageNo % _pageSize == 0) {
			_pRCnt = parseInt(_pageNo / _pageSize) - 1; 
		}
		
		//first 화살표	
		if(_pageNo > _pageSize) { 
			html.push('<li class="page-nav"><a class="page-link" href="javascript:' + token + '(1)">«</a></li>');
			 
		} else { 
			html.push('<li class="page-nav disabled"><a class="page-link" href="javascript:void(0)">«</a></li>');			
		}
		
		//이전 화살표
		if(_pageNo - 10 > 0) {
			html.push('<li class="page-nav"><a class="page-link" href="javascript:' + token + '('+ (_pageNo - 10) +')">‹</a></li>');
		}else {
			html.push('<li class="page-nav disabled"><a class="page-link" href="javascript:void(0)">‹</a></li>');
		}
		
		//paging Bar 
		for(var index=_pRCnt * _pageSize + 1; index < (_pRCnt + 1) * _pageSize + 1; index++) {
			if(index == _pageNo) {
				html.push('<li class="page visible active"><a class="page-link" href="javascript:void(0)">'+ index +'</a></li>');
			} else { 
				html.push('<li class="page visible"><a class="page-link" href="javascript:'+ token +'('+index+')">'+index+'</a></li>');
			} 
			
			if(index == _pageCnt) {
				break; 
			} 
		} 
		
		//다음 화살표
		if(_pageCnt - _pageNo > 10) {
			html.push('<li class="page-nav"><a class="page-link" href="javascript:'+token+'('+ (_pageNo +10) +')">›</a></li>');
		} else {
			html.push('<li class="page-nav disabled" data-page="next"><a class="page-link" href="javascript:void(0)">›</a></li>');
		}
		
		//last 화살표
		if(_pageCnt > (_pRCnt + 1) * _pageSize) {
			html.push('<li class="page-nav"><a class="page-link" href="javascript:'+token+'('+ _pageCnt +')">»</a></li>'); 
		} else {
			html.push('<li class="page-nav disabled" data-page="next"><a class="page-link" href="javascript:void(0)">»</a></li>');
		}
		
		return html.join(""); 
		 
	}
	
}


