$(document).ready(function(){  
    $("#deptTreeDiv").jstree({
        core : {
		    "multiple" : false,
		    "animation" : 0,
    		"check_callback" : true,
		    'data' : {
			    'url' : basePath+"organise/getOrganiseTree.do" ,
			    'data' : function (node) {
			    	if(node.id === '#'){
			    		return  { 'parOrgId' : '-1' };
			    	}
			      return { 'parOrgId' : node.id };
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
					$("#deptId").val(id);
					$("#deptName").val(text);
				}
		});  
}); 