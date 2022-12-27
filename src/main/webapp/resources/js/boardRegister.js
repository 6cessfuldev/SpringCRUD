document.getElementById('trigger').addEventListener('click', ()=> {
    document.getElementById('formFileMultiple').click();
});

//정규표현식을 이용한 생성자 함수를 만들기
//fileload 시 형식 제한 함수
//실행파일 막기
//이미지 파일
const regExp = new RegExp("\.(exe|sh|bat|msi|dll|js)$"); //실행파일 막기
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif)$");
const maxSize = 1024*1024*20; //20MB

function fileSizeValidation(fileName, fileSize){
    if(regExp.test(fileName)){
        return 0;
    }else if(!regExpImg.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}

document.addEventListener("change", (e)=>{
    if(e.target.id == "formFileMultiple"){
        console.log("changed");
        document.getElementById('regBtn').disabled = false;
        const fileObject = document.getElementById('formFileMultiple').files;
        console.log(fileObject);

        let div = document.getElementById("fileZone");
        div.innerHTML="";
        let ul = '<ul class="list-group list-group-flush">';
        let isOk = 1;
        for(let file of fileObject){
            let validResult = fileSizeValidation(file.name, file.size);
            isOk *= validResult;
            ul+=`<li class="list-group-item d-flex justify-content-between align-items-start">`;
            //업로드 가능여부 표시
            ul+= `${validResult ? '<div class="fw-bold">업로드 가능' : '<div class="fw-bold text-danger">업로드불가'}</div>`; 
            ul+= `${file.name}`;
            ul+= `<span class="badge bg-${validResult ? 'success':'danger'} rounded-pill">${file.size}Bytes</span></li>`;
        }
        ul += '</ul>';
        div.innerHTML=ul;
        
        if(isOk == 0){
        	document.getElementById('regBtn').disabled = true;
        }
    }
})