import { useEffect, useState } from "react";
import { PublicUser } from "../services/UserService";
import { getUserNetwork } from "../services/NetworkService";
import ConnectionListItemComponent from "./ConnectionListItemComponent";
import AddNewGroupComponent from "./group/AddNewGroupComponent";
import Modal from "../common/Modal";
import ListGroupCandidatesComponent from "./group/ListGroupCandidatesComponent";
import { getGroup, Group } from "../services/GroupService";
import GroupItemComponent from "./group/GroupItemComponent";

const ListConnectedComponent = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [friends, setFriends] = useState<PublicUser[]>([]);
  const [group, setGroup] = useState<Group | null>(null);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const group = await getGroup();
        setGroup(group);
        const network = await getUserNetwork();
        setFriends(network);
      } catch (err) {
        console.error("Failed to fetch users", err);
      }
    };
    fetchUsers();
  }, []);

  const handleNewGroup = (newGroup: Group) => {
    setGroup(newGroup);
  };

   const handleAddParticipant = (updatedGroup: Group) => {
    setGroup(updatedGroup);
  };
  
  return (
    <section className="p-4">
      <h4>Group</h4>
      {group ? (
        <GroupItemComponent participants={group.users}/>
      ) : (
        <AddNewGroupComponent onClick={() => setIsOpen(true)} />
      )}
      <h4>Friends</h4>
      {isOpen && (
        <Modal onClose={() => setIsOpen(false)}>
          <ListGroupCandidatesComponent
            newGroup={handleNewGroup}
            groupCreated={group !== null}
          />
        </Modal>
      )}
      <ul className="py-4 flex flex-col gap-2">
        {friends.map((friend, index) => (
          <ConnectionListItemComponent key={index} user={friend} addParticipant={handleAddParticipant}/>
        ))}
      </ul>
    </section>
  );
};

export default ListConnectedComponent;
