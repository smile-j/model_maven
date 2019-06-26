$(document).on("summerready",function () {
	var i = 0;
	console.log(i);
    var vm = new Vue({
		el: "#app",
		data:{
			paper:[],
			attachment:[],
			workflow:[]
		},
		filters:{

		},
		mounted: function () {
			this.$nextTick(function () {
				// 代码保证 this.$el 在 document 中
				this.createView();
			})
		},
		methods:{
			createView:function(){
				var data = {};
			}
		}
	});
})
