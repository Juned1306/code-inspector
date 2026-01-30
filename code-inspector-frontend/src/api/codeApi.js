import axios from "axios";
export const inspectCode=(code)=>{
return
axios.post("http://localhost:8080/api/inspect",{
code,
});
};