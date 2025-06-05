
export const VerificationProcess = () => {
  return (
    <div className="bg-white rounded-md h-50 p-8 full sm:min-w-2/3 flex flex-col justify-between">
      <h4 className="text-xl"> Your account needs verification</h4>
      <p className="text-gray-400">Please fill all the missing data and complete all the steps to get verified by an admin</p>
      <ul className="sequence flex justify-evenly items-center  w-full relative my-8">
        <li className="step"></li>
        <li className="step"></li>
        <li className="step"></li>
        <li className="step"></li>
        <li className="step"></li>
        <span
          id="progress"
          className="w-3/6 h-1 absolute bg-accent left-0 bottom-1/2 translate-y-1/2 transition-all"
        ></span>
      </ul>
    </div>
  );
};
