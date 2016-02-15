Vue.component('require', {
  template: '#require',
  props: ['item'],
  methods: {
      dump: function() {
          console.log('liangliang' + JSON.stringify(this.item))
          var path = this.item.path
          var pathComponents = path.split('/')
          var caseName = pathComponents.pop()
          caseName=prompt("Please input case name:", caseName + ".js");
          var client = proxyClient('localhost', 1080);
          client.dumpToCaseJSON(this.item, caseName)
      },
      detail: function() {
          
      },
  }
})

Vue.filter('string', function(value) {
    if (value) {
        return value.map(function(obj){
            return JSON.stringify(obj);  
        }).toString()
    } else {
        return ""
    }
})
