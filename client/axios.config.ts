import axios from "axios";
import { API_URL } from "./src/utils/env";

const instance = axios.create({
  baseURL: API_URL,
  /*headers: {
      accept: "application/json",
      Authorization: getAccessToken(),
    },*/
});

export default instance;
