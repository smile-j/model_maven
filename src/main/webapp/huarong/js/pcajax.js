//TaskId: {4:'处室负责人审核',6:'会签',7:'拟稿人办理',9:'领导人签发'}
//modelId: 437  modelName:股份有限公司部门发文
$.extend({
    _getHRData: function (data) {
        var modelId = data.WorkFlowInfo.ModelId;
        var taskId = data.WorkFlowInfo.TaskId;
        var modelContent = modelDetailData["ModelId-"+modelId]["TaskId-"+taskId];
        var Context = data.Context;
        var newContext = []
        //[{name:"AuthorName",ch:"拟稿人",type:"null",content:""李青}]
        for (var key in Context) {
            var value = Context[key];
            if (value) {
              var modelContentObj = modelContent[key];
                for (var i in modelContentObj) {
                  temp[i] = modelContentObj[i]
                }
                //var temp = Object.assign({},modelContent[key])
                temp.name = key;
                temp.content = Context[key];
                newContext.push(temp)
            }
        }
        data.Context = newContext;
        return data;
    }
})



