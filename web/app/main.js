new Vue({
  el: '#main',
  data: {
    message: 'Hello Vue.js!',
    requires: [],
    cases: []
  },
  ready: function() {
      var client = proxyClient('localhost', 1080)
      this.requires = client.retrieve()
      console.log(this.requires)
      
      var mockServer = mockServerClient('localhost', 1080)
      this.cases = mockServer.retrieveCases()
      console.log(this.cases)
      
  }
})