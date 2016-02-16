Vue.component('require', {
  template: '#require',
  props: ['item'],
  methods: {
      dump: function() {
          console.log('liangliang' + JSON.stringify(this.item))
          var path = this.item.path
          var pathComponents = path.split('/')
          var caseName = pathComponents.pop()
          caseName=prompt("Please input case name:", caseName);
          var client = proxyClient('localhost', 1080);
          client.dumpToCaseJSON(this.item, caseName + ".js")
      },
      detail: function() {
          
      },
  }
})
