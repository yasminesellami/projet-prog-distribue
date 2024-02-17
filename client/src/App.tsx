import "./index.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import MovieLibrary from "./pages/MovieLibrary";
import Movie from "./pages/Movie";
import { useEffect } from "react";
const router = createBrowserRouter([
  {
    path: "/",
    element: <MovieLibrary />,
  },
  {
    path: "/movie/:movieId",
    element: <Movie />,
  },
]);

export default function App() {
  useEffect(() => {
    document.body.classList.add("dark:bg-slate-800");
  }, []);
  return <RouterProvider router={router} />;
}
