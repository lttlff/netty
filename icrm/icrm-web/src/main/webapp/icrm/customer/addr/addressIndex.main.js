$(function() {

			var $addrSubGridFrame = $("#addrSubGrid");

			var $tree = $('#addrTree').jstree({
				'core' : {
					"animation" : 0,
					"multiple" : false,
					"check_callback" : true,
					'data' : {
						// url:basePath + "addrController/tree.do?",
						url : basePath + 'addrController/tree.do',
						'data' : function(node) {
							if (node.id === '#') {
								return {
									'parentAddrId' : null
								};
							}
							return {
								'parentAddrId' : node.id
							};
						}
					}
				},
				"plugins" : ["themes", "json_data", "ui", "crrm", "cookies",
						"dnd", "search", "types", "hotkeys", "contextmenu",
						"core"]
			}).on('changed.jstree', function(e, data) {
				if (data && data.selected && data.selected.length) {
					var id = data.selected;
					var text = data.instance.get_node(data.selected).text;
					$addrSubGridFrame.attr("src", basePath + "addrController/addressList.do?parentAddrId="+id);
				}
			})

		})
