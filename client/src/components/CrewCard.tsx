import { IMAGE_URL, crew } from ".";

interface Props {
  crew: crew;
}

export default function CrewCard({ crew }: Props) {
  let src = "";
  if (crew.profilePath != "null") {
    src = IMAGE_URL + crew.profilePath;
  }
  return (
    <div className="min-w-[200px]">
      <img src={src} alt="profile" />
      <p className="text-sm">{crew.name}</p>
      <p className="text-stone-300 text-sm">{crew.job}</p>
    </div>
  );
}
