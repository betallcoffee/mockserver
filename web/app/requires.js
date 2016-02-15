Vue.component('require', {
  template: '#require',
  props: ['item'],
  methods: {
      mock: function() {
          console.log('liangliang' + JSON.stringify(this.item))
      }
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
