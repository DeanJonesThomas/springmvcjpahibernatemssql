<form action="/ecommerce/cc.do" method="POST">

<div class="container">
    <div class="col-sm-6">
        <fieldset>
            <legend>Payment</legend>
            <div class="control-group">
                <label label-default="" class="control-label">Card Holder's Name</label>
                <div class="controls">
                    <input type="text" class="form-control" pattern="\w+ \w+.*" title="First and last name" required="" id="ccHolder" name="ccHolder">
                </div>
            </div>
            <div class="control-group">
                <label label-default="" class="control-label">Card Number</label>
                <div class="controls">
                    <div class="row">
                        <div class="col-md-12">
                            <input type="text" class="form-control" autocomplete="off" maxlength="16" title="Credit Card Number"
                             required="" id="creditCardNumber" name="creditCardNumber">
                        </div>
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label label-default="" class="control-label">Card Expiry Date</label>
                <div class="controls">
                    <div class="row">
                        <div class="col-md-9">
                            <input type="text" class="form-control" autocomplete="off" maxlength="4" pattern="\d{4}" title="Expiry Date" required="" id="exprDate" name="exprDate">
                        </div>
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label label-default="" class="control-label">Card CVV</label>
                <div class="controls">
                    <div class="row">
                        <div class="col-md-3">
                            <input type="text" class="form-control" autocomplete="off" maxlength="3" pattern="\d{3}" title="Three digits at back of your card" required="">
                        </div>
                        <div class="col-md-8"></div>
                    </div>
                </div>
            </div>
            <div class="control-group">
              <label label-default="" class="control-label"></label>
              <div class="controls">
                <button type="submit" class="btn btn-primary">Submit</button> 
                <button type="button" class="btn btn-default">Cancel</button>
              </div>
            </div>
        </fieldset>
    </div>
</div>
<hr>
</form>