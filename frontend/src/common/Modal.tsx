interface ModalProps {
  children: React.ReactNode;
  onClose: () => void;
}

const Modal = ({ children, onClose }: ModalProps) => {
  return (
    <div className="fixed top-0 w-full inset-0 backdrop-blur-xl backdrop-saturate-50 z-50 flex items-center justify-center">
      <div className="bg-white p-4 rounded-xl shadow-lg relative flex justify-center items-center w-[350px] m-2 sm:w-1/2">
        <button
          onClick={onClose}
          className="absolute top-3 right-3 bg-blue-500 size-10 hover:text-black rounded-lg z-10"
        >
          <svg className="size-9 absolute top-1/2 left-1/2 -translate-1/2 p-1">
            <use xlinkHref="/assets/icons.svg#cancel"></use>
          </svg>
        </button>
        {children}
      </div>
    </div>
  );
};

export default Modal;
