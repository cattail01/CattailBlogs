export default {
    data() {
        return {
            count: 0
        }
    },
    methods:{
        AddOneWhenClick(){
            this.count++
            console.log(this.count)
        }
    },
};
