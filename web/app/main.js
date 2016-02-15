new Vue({
  el: '#main',
  data: {
    message: 'Hello Vue.js!',
    requires:[]
  },
  ready: function() {
      var client = proxyClient('localhost', 1080)
      var response = client.retrieve()
      console.log(response)
      this.requires = response
      
      var mockServer = mockServerClient('localhost', 1080)
      mockServer.mockCaseResponse('ScenicIndex.js')
      
  }
})