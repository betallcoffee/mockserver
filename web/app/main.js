new Vue({
  el: '#main',
  data: {
    message: 'Hello Vue.js!',
    requires: [],
    cases: []
  },
  ready: function() {
      this.retrieveRequest()
      this.retrieveCase()
  },
  methods: {
      retrieveRequest: function() {
          var client = proxyClient('localhost', 1080)
          this.requires = client.retrieve()
          console.log(this.requires)
      },
      reset: function() {
          var client = proxyClient('localhost', 1080)
          client.reset()
      },
      retrieveCase: function() {
          var mockServer = mockServerClient('localhost', 1080)
          this.cases = mockServer.retrieveCases()
          console.log(this.cases)
      }
  }
})