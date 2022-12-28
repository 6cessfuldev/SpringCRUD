async function postCommentToServer(cmtData){
 	
 try{
 	const url = '/comment/'+cmtData.bno;
 	console.log(cmtData.bno);
 	const config = {
 		method : 'post',
 		headers : {
 			'content-type':'application/json; charset=utf-8'
 		},
 		body:JSON.stringify(cmtData)
 	};
 	
 	let resp = await fetch(url, config);
 	let result = await resp.text();
 	return result;
	}catch(error){
		console.log(error);
	}
}

document.getElementById('cmtPostBtn').addEventListener('click',()=>{
	const cmtText = document.getElementById('cmtText').value;
	console.log(cmtText);
	if(cmtText == null || cmtText==''){
		alert("댓글을 입력해주세요.");
		cmtText.focus();
		return false;
	}else{
		let cmtData = {
			bno : bnoVal,
			writer : document.getElementById('cmtWriter').innerText,
			content : cmtText
		};
		console.log(cmtData);
		postCommentToServer(cmtData).then(result => {
			if(result>0){
				alert("댓글 등록 성공");
			}
			getCommentList(cmtData.bno);
		});
	}
});

async function spreadCommentFromServer(bno){
	console.log("spreadCommentFromServer "+bno);
	
	try{
		let resp = await fetch("/comment/"+bno);
		let result = await resp.json();
		return result;
	}catch(error){
		console.log(error);
	}

}

function getCommentList(bno){

	spreadCommentFromServer(bno).then(result => {
	
		console.log(result);
		const ul = document.getElementById('cmtListArea');
		ul.innerHTML = "";
		if(result.length > 0){
			for(let cvo of result){
				let li = `<li data-cno="${cvo.cno}" class="list-group-item d-flex justify-content-between align-items-start">`;
				li+=`<div class="ms-2 me-auto"><div class="fw-bold">${cvo.writer}</div>`;
				li+=`<input type="text" class="form-control" id="cmtTextMod" value="${cvo.content}"></div>`;
				li+=`<span class="badge bg-dark rounded-pill">${cvo.mod_at}</span>`;
				li+=`<button class="btn btn-sm btn-outline-warning mod" id="cmtPostBtn" type="button">%</button>`;
				li+=`<button class="btn brn-sm btn-outline-danger del" id="cmtPostBtn" type="button">X</button>`;
				li+=`</li>`;
				ul.innerHTML += li;
			} 
		}else {
			ul.innerHTML +=`<li class="list-group-item d-flex justify-content-between aligh-items-start">Comment List Page</li>`;
		}
	})
}

async function editCommentToServer(cmtDataMod){
	try{
	
		const url='/comment/'+cmtDataMod.cno;
		const config = {
			method : "put",
			headers : {
				'content-type':'application/json; charset=utf-8'
			},
			body : JSON.stringify(cmtDataMod)
		};
		const resp = await fetch(url, config);
		const result = await resp.text();
		return result;
	
	}catch(error){
		console.log(error);
	}
}

async function removeCommentToServer(cno){
	try{
	
		const url='/comment/'+cno;
		const config = {
			method : "delete",
		};
		const resp = await fetch(url, config);
		const result = await resp.text();
		return result;
	
	}catch(error){
		console.log(error);
	}
}

document.addEventListener('click', (e)=>{

	if(e.target.classList.contains('mod')){
	
		let li = e.target.closest('li');
		let cnoVal = li.dataset.cno;
		let textContent = li.querySelector('#cmtTextMod').value;
		let cmtDataMod = {
			cno : cnoVal,
			content : textContent
		}
		console.log(cmtDataMod);
		editCommentToServer(cmtDataMod).then(result => {
			if(result>0){
				alert("댓글 수정 성공");
			}
			getCommentList(bnoVal);
		})
	
	}else if(e.target.classList.contains('del')){
		
		let li = e.target.closest('li');
		let cnoVal = li.dataset.cno;
		removeCommentToServer(cnoVal).then(result => {
			if(result>0){
				alert("댓글 삭제 성공");
			}
			getCommentList(bnoVal);
		})
	}
	
})