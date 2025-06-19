import { useEffect, useState } from "react";
import { PublicUser } from "../../services/UserService";
import { getGroupCandidates } from "../../services/GroupService";
import GroupCandidateListItemComponent from "./GroupCandidateListItemComponent";
import { addGroup, Group } from "../../services/GroupService";

const ListGroupCandidatesComponent = ({
  newGroup, groupCreated
}: {
  newGroup: (group: Group) => void; groupCreated: boolean
}) => {
  const [candidates, setCandidates] = useState<PublicUser[]>([]);
  const [selected, setSelected] = useState<string[]>([]);

  useEffect(() => {
    const fetchCandidates = async () => {
      try {
        const res = await getGroupCandidates();
        setCandidates(res);
      } catch (err) {
        console.error("Failed to fetch users", err);
      }
    };
    fetchCandidates();
  }, []);

  const handleClick = (candidate: PublicUser) => {
    setSelected((sel) =>
      sel.includes(candidate.username)
        ? sel.filter((username) => username !== candidate.username)
        : [candidate.username, ...sel]
    );
  };

  const handleCreateGroup = async () => {
    try {
      const group = await addGroup(selected);
      newGroup(group);
      setSelected([]);
    } catch (error) {
      console.error("Failed to create group:", error);
    }
  };

  return (
    <section className="p-10 max-h-3/4 flex flex-col items-center gap-4">
      {!groupCreated ? (
        <>
          <h4 className="text-center">{candidates.length > 0 ? "Invite friends to your group" : "You don't have any friends to invite!"}</h4>
          <ul className="py-4 flex flex-col gap-2 overflow-y-auto">
            {candidates.map((candidate, index) => (
              <GroupCandidateListItemComponent
                key={index}
                user={candidate}
                onClick={() => handleClick(candidate)}
              />
            ))}
          </ul>
          <button
            onClick={handleCreateGroup}
            className={`${
              selected.length > 0
                ? "bg-blue-500 hover:bg-blue-600  text-white"
                : "bg-box text-gray-400"
            } p-3 w-1/2 rounded-sm font-bold`}
            disabled={selected.length === 0}
          >
            Create Group
          </button>
        </>
      ) : (
        <div className="bg-green-300 flex rounded-lg p-4 mt-8 items-center">
          <svg className="plus-icon size-7 text-muted flex items-center justify-center">
            <use xlinkHref="assets/icons.svg#tick"></use>
          </svg>
          <p className="text-white">You have created a Group</p>
        </div>
      )}
    </section>
  );
};

export default ListGroupCandidatesComponent;
