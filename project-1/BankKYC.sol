//SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract BankKYC {

    struct Bank {
        string name;
        address bankAddress;
        uint kycticks;
        bool canAdd; 
        bool canKYC;
    }

    enum Status {
        Pending,
        Inprocess,
        Accepted,
        Rejected,
        Canceled
    }

    struct Customer {
        uint id;
        string name;
        string data;
        address bankAddress;
        Status kycStatus; 
    }

    address private owner;
    Bank private bank;
    Customer[] public customers;

    modifier onlyOwner() {
        require(msg.sender == owner, "BANK: Only owner can call");
        _;
    }

    constructor(string memory _name, address _bankAddress) {
        owner = msg.sender;
        // 1. Add new bank to blockchain - constructor (onlyowner)
        // Allow permission by default
        bank = Bank({name: _name, bankAddress: _bankAddress, kycticks: 0, canAdd: true, canKYC: true});

    }

    // can anyone add new customer or only owner
    function addCustomer(uint _id, string memory _name, string memory _data, address _bankAddress) public {
        // check if bank can new customer 
        require(bank.canAdd == true, "Bank: should have privilege to add new customer");
        // Hardcode customer id or Counter
        customers.push(Customer({id: _id, name: _name, data: _data, bankAddress: _bankAddress, kycStatus: Status.Pending}));
    }

    function checkKYC() public { }

    function performKYC(uint _id, Status _status) public { 
        //check current status of kyc
        // change status here
        customers[_id].kycStatus = _status;
    }

    function blockKYC() public onlyOwner { 
        bank.canKYC = false;
    }

    function allowKYC() public onlyOwner { 
        bank.canKYC = true;
    }

    // 5. Block bank from adding new customer 
    function blockAdd() public { }

    // 7. Allow adding new customer
    function allowAdd() public { } 
    
    function customerInfoById(uint _id) view public returns(string memory, string memory, address, Status){ 
        // check if customer exists
        return (
            customers[_id].name,
            customers[_id].data,
            customers[_id].bankAddress,
            customers[_id].kycStatus
        );
    }

    // 2. Add new customer to bank
    // 3. Check KYC status of existing bank customer
    // 4. Perform KYC and update the status 
    
    // 6. Block bank from doing KYC
    
    // 8. Allow doing KYC
    // 9. View customer information
}