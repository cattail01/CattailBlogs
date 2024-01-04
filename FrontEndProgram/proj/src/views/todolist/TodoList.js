export default {
    data(){
        return {
            MyText: "",
            TodoList: ["test", "data"]
        }
    },
    methods: {
        HandleAddData(){
            console.log("add data success!")
        },
        HandleInputData(){
            console.log("input data: ")
        }
    }
}