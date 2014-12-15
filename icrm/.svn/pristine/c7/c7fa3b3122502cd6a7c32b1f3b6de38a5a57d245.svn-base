var tree =null;
$(document).ready(function(){  
     tree = $("#posTreeDiv").jstree({
        core : {
		    "multiple" : false,
		    "animation" : 0,
    		"check_callback" : true,
		    'data' : {
			    'url' : basePath+"position/getPositionTree.do" ,
			    'data' : function (node) {
			    	if(node.id === '#'){
			    		return  { 'parPosId' : '-1' };
			    	}
			      return { 'parPosId' : node.id };
			    }
			  },
			'themes' : {
				'responsive' : false,
				'variant' : 'small',
				'stripes' : true
			},
			'types' : {
				'default' : { 'icon' : 'folder' },
				'file' : { 'valid_children' : false, 'icon' : 'file' }
			}
		  },
		  plugins : ["themes",'state','dnd', "types",'sort']
    })
    .on('changed.jstree', function (e, data) {
				if(data && data.selected && data.selected.length) {
					var id = data.selected;
					var text = data.instance.get_node(data.selected).text;
					$("#mainFrame").attr("src",basePath + "position/main.do?parPosId=" + id);
//					$("#deptId").val(id);
//					$("#deptName").val(text);
				}
		});  
}); 

function reloadTree(){
	var nodeid =   $("#posTreeDiv").jstree("get_selected");
	var node =   $("#posTreeDiv").jstree("get_node",nodeid);
	$("#posTreeDiv").jstree("refresh_node",nodeid);
}