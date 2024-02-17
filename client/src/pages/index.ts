import { useQuery } from "react-query";
import { IMAGE_URL } from "../utils/env";
import Poster from "../components/Poster";
import { listNowPlayingMovies } from "../services/movies.services";
import { nowPlayingMovie } from "../types/nowPlayingMovie";

import { useParams } from "react-router-dom";
import { getMovie } from "../services/movies.services";
import BackButton from "../components/BackButton";
import MovieDetails from "../components/MovieDetails";
import Credits from "../components/Credits";
import Images from "../components/Images";
import { useState, useEffect, useMemo } from "react";
import Skeleton from "../components/Skeleton";

export {
  useQuery,
  listNowPlayingMovies,
  IMAGE_URL,
  Poster,
  useParams,
  getMovie,
  BackButton,
  MovieDetails,
  Credits,
  Images,
  useState,
  useEffect,
  useMemo,
  Skeleton,
};
export type { nowPlayingMovie };
