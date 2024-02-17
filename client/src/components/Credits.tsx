import { cast, CastCard, crew } from ".";
import CrewCard from "./CrewCard";

interface props {
  casts: cast[];
  crews: crew[];
}
export default function Credits({ casts, crews }: props) {
  return (
    <section>
      <h1 className="text-3xl">Credits</h1>
      <ul className="flex gap-4 overflow-x-auto">
        {casts.map((cast, key) => {
          return <CastCard cast={cast} key={key} />;
        })}
        {crews.map((crew, key) => {
          return <CrewCard crew={crew} key={key} />;
        })}
      </ul>
    </section>
  );
}
