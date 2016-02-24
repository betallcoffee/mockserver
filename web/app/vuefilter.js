Vue.filter('string', function(value) {
    if (value) {
        return value.map(function(obj){
            return JSON.stringify(obj);  
        }).toString()
    } else {
        return ""
    }
})