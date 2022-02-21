
<script>
export default {
  name: 'SyncAsyncExample',
  data () {
  },
  methods: {
    // synchronously prints
    printABC_sync(){
      console.log('A');
      console.log('B');
      console.log('C');
    },
    // async example with callback
    asyncFunctionWithCallback (){
      console.log('A1');
      setTimeout(function(){
        console.log('B1')
      }, 1000);
      console.log('C1')
    },
    // async example with predefined callback function
    printB1 (){
      console.log('B2');
    },
    asyncFunctionWithCallback_2 (){
      console.log('A2');
      setTimeout(this.printB1, 1000);
      console.log('C2')
    },
    // promise encapsulating async operation
    delay() {
      return new Promise((resolve) => {
        setTimeout(resolve, 1000);
      });
    },
    asyncFunctionWithPromise (){
      console.log('A3');
      const prom = this.delay();
      prom.then(()=>{
        console.log('B3')
      });
      console.log('C3')
    },
    // print after 1 sec timeout, before resolving promise
    delay_and_print() {
      return new Promise((resolve) => {
        setTimeout(() => {
          console.log('B4')
          resolve();
        }, 1000);
      });
    },
    asyncFunctionWithPromise_2 (){
      console.log('A4');
      this.delay_and_print();
      console.log('C4')
    },
    // input output with Promises
    // for example set timeout
    deplay_T_and_return_D(time) {
      return new Promise ((resolve) => {
        setTimeout(()=>{
          resolve('D');
        }, time)
      })
    },
    async asyncFunctionWithPromise_3(){
      console.log('A5');
      await this.deplay_T_and_return_D(2000).then(
          data => {
            console.log(data);
          }
      );
      console.log('C5')
    }
  },
  mounted() {
    this.printABC_sync();
    this.asyncFunctionWithCallback();
    this.asyncFunctionWithCallback_2();
    this.asyncFunctionWithPromise();
    this.asyncFunctionWithPromise_2();
    this.asyncFunctionWithPromise_3();
  }
}
</script>
