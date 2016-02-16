Vue.component('case', {
  template: '#case',
  props: ['item'],
  methods: {
      mock: function() {
          console.log('liangliang' + JSON.stringify(this.item))
          var mockServer = mockServerClient('localhost', 1080)
          mockServer.mockCaseResponse(this.item)
      },
      detail: function() {
          
      },
  }
})