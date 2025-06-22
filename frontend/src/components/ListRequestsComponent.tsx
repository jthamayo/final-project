import { useEffect, useState } from "react";
import RequestListItemComponent from "./RequestListItemComponent";
import {
  Request,
  getSentNetworkRequests,
  getReceivedNetworkRequests,
} from "../services/RequestService";

const ListRequestsComponent = () => {
  const [received, setReceived] = useState<Request[]>([]);
  const [sent, setSent] = useState<Request[]>([]);

  useEffect(() => {
    const fetchRequests = async () => {
      try {
        const recipient = await getReceivedNetworkRequests();
        setReceived(recipient);
        const sender = await getSentNetworkRequests();
        setSent(sender);
      } catch (err) {
        console.log(err);
      }
    };

    fetchRequests();
  }, []);

  return (
    <section className="p-4">
      <h4>New Requests</h4>
      <ul className="py-4 flex flex-col gap-2">
        {received.length != 0 ? (
          received.map((r, index) => (
            <RequestListItemComponent
              request={r}
              isReceived={true}
              key={index}
            />
          ))
        ) : (
          <li className="text-red-500 w-full">
            <div className="flex items-center justify-center  bg-red-300 m-4 rounded-sm">
              <div className="addGroup flex items-center text-white justify-center gap-2 p-4">
                <p className="text-lg">You don't have any new requests</p>
                <svg className="icon size-8">
                  <use xlinkHref="/assets/icons.svg#exclamation"></use>
                </svg>
              </div>
            </div>
          </li>
        )}
      </ul>
      <h4>Your requests</h4>
      <ul className="py-4 flex flex-col gap-2">
        {sent.length != 0 ? (
          sent.map((s, index) => (
            <RequestListItemComponent
              request={s}
              isReceived={false}
              key={index}
            />
          ))
        ) : (
          <li className="text-red-500 w-full">
            <div className="flex items-center justify-center  bg-red-300 m-4 rounded-sm">
              <div className="addGroup flex items-center text-white justify-center gap-2 p-4">
                <p className="text-lg">You don't have any pending requests</p>
                <svg className="icon size-8">
                  <use xlinkHref="/assets/icons.svg#exclamation"></use>
                </svg>
              </div>
            </div>
          </li>
        )}
      </ul>
    </section>
  );
};

export default ListRequestsComponent;
