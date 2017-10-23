/**
 * Created by zhengsu on 16/12/28.
 */
function showOrHideChooseTypeTable(e){
    e.stopPropagation();
    var chooseTypeTable = document.getElementsByClassName("chooseTypeTable")[0];
    if(chooseTypeTable){
        if(chooseTypeTable.style.display=='block'){
            chooseTypeTable.style.display='none';
        }else{
            chooseTypeTable.style.display='block';
        }
    }
}

function selectType(){
    var typeList = document.getElementsByClassName("typeList")[0].getElementsByTagName("li");
    for (i=0;i<typeList.length;i++){
        typeList[i].onclick = function(e){
            e.stopPropagation();
            var p = this.getElementsByTagName("p")[0];
            chooseTypeBox.innerHTML = p.innerHTML;
            var parent = this.parentNode;
            var lis = parent.getElementsByTagName("li");
            var ps = parent.getElementsByTagName("p");
            removeArrClass(lis, "current");
            removeArrClass(ps, "current");
            addClassName(this, "current");
            addClassName(p, "current");
            var chooseTypeTable = document.getElementsByClassName("chooseTypeTable")[0];
            chooseTypeTable.style.display='none';
            var type = this.getAttribute("id-type");
            chooseTypeBox.setAttribute("id-type",type);
        }
    }
}